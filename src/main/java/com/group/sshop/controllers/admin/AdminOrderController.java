package com.group.sshop.controllers.admin;

import com.group.sshop.models.enums.OrderStatus;
import com.group.sshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {
    private final OrderService orderService;

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("admin/orders");
    }

    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/order-details");
        modelAndView.addObject("order", orderService.findById(id));
        return modelAndView;
    }

    @GetMapping("/{id}/payment/confirm")
    public ModelAndView confirmPayment(@PathVariable Long id) {
        orderService.confirmPayment(id);
        return new ModelAndView("redirect:/admin/orders/" + id);
    }

    @GetMapping("/{id}/status")
    public ModelAndView setStatus(@PathVariable Long id,
                                  @RequestParam
                                  OrderStatus status) {
        orderService.setStatus(id, status);
        return new ModelAndView("redirect:/admin/orders/" + id);

    }
}
