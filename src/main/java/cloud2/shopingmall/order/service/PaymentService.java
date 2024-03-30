package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.entity.Delivery;
import org.springframework.stereotype.Service;
import cloud2.shopingmall.order.repository.PaymentRepository;
import cloud2.shopingmall.order.entity.Payment;
import cloud2.shopingmall.order.dto.PaymentDTO;
import cloud2.shopingmall.order.exception.ResourceNotFoundException;

@Service
public class PaymentService {
    /**
     * 결제 진행
     * 결제 확인및 검증
     * 결제 취소 및 환불 처리
     */

    private PaymentRepository paymentRepository;

    public PaymentDTO createPayment(Integer payTotalPrice, PaymentDTO.PayStatus payStatus) {
        Payment payment = new Payment();
        payment.setPayTotalPrice(payTotalPrice);
        payment.setPayStatus(payStatus);
        // 생성일시 및 수정일시 설정 등

        paymentRepository.save(payment);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPayTotalPrice(payment.getPayTotalPrice());
        paymentDTO.setPayCreatedAt(payment.getPayCreatedAt());
        paymentDTO.setPayModifiedAt(payment.getPayModifiedAt());
        paymentDTO.setPayStatus(payment.getPayStatus());

        return paymentDTO;
    }

    public PaymentDTO getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", "Id", paymentId));

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPayTotalPrice(payment.getPayTotalPrice());
        paymentDTO.setPayCreatedAt(payment.getCreatedAt());
        paymentDTO.setPayModifiedAt(payment.getModifiedAt());
        paymentDTO.setPayStatus(payment.getPayStatus());

        return paymentDTO;
    }

    public Payment updatePaymentStatus(Long paymentId, Payment.PayStatus newStatus) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", "id", paymentId));

        payment.setPayStatus(newStatus);
        return paymentRepository.save(payment);
    }
}
