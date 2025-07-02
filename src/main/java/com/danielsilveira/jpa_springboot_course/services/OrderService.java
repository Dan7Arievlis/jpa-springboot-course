package com.danielsilveira.jpa_springboot_course.services;

import com.danielsilveira.jpa_springboot_course.entities.Order;
import com.danielsilveira.jpa_springboot_course.entities.OrderItem;
import com.danielsilveira.jpa_springboot_course.entities.Payment;
import com.danielsilveira.jpa_springboot_course.entities.Product;
import com.danielsilveira.jpa_springboot_course.entities.enums.OrderStatus;
import com.danielsilveira.jpa_springboot_course.repositories.OrderItemRepository;
import com.danielsilveira.jpa_springboot_course.repositories.OrderRepository;
import com.danielsilveira.jpa_springboot_course.services.exceptions.DatabaseException;
import com.danielsilveira.jpa_springboot_course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Order insert(Order order) {
        return repository.save(order);
    }

    public void delete(Long id) {
        try {
            Order order = repository.getReferenceById(id);
            if (order.getOrderStatus() == OrderStatus.WAITING_PAYMENT || order.getOrderStatus() == OrderStatus.CANCELED) {
                repository.deleteById(id);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Order update(Long id, Order order) {
        try {
            Order entity = repository.getReferenceById(id);
            updateData(entity, order);
            return repository.save(entity);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        } catch(JpaSystemException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Order entity, Order order) {
        entity.setOrderStatus(order.getOrderStatus());
        if (order.getPayment() != null)
            if (order.getId() != null)
                if (Objects.equals(entity.getId(), order.getId()))
                    entity.setPayment(new Payment(null, order.getPayment().getMoment(), entity));
                else
                    throw new DatabaseException("Entity id doesn't match");
            else
                throw new DatabaseException("Order Object incomplete. Need to provide an id.");
        else
            entity.setPayment(null);
    }

    public Order addItem(Long id, OrderItem orderItem) {
        try {
            Order entity = repository.getReferenceById(id);
            orderItem.setPrice(orderItem.getProduct().getPrice());
            orderItem.setOrder(entity);
            if (entity.getItems().stream().filter(e -> e.getProduct().equals(orderItem.getProduct())).findAny().isEmpty()) {
                entity.getItems().add(orderItem);
                orderItemRepository.save(orderItem);
            } else {
                entity.getItems().stream().filter(
                        item ->
                                item.getProduct().equals(orderItem.getProduct())).findFirst().ifPresent(
                        item -> {
                            item.setQuantity(item.getQuantity() + orderItem.getQuantity());
                            orderItemRepository.save(item);
                        });
            }

            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Order removeItem(Long id, OrderItem orderItem) {
        try {
            Order entity = repository.getReferenceById(id);
            entity.getItems().stream().filter(
                    item ->
                            item.getProduct().equals(orderItem.getProduct())).findFirst().ifPresent(
                    item -> {
                        item.setQuantity(item.getQuantity() - orderItem.getQuantity());
                        if (item.getQuantity() <= 0)
                            entity.removeItem(item.getProduct());
                    });
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
