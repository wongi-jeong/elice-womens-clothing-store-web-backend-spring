package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.dto.PaymentDTO;
import cloud2.shopingmall.order.repository.PaymentRepository;
import cloud2.shopingmall.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentService {
    /**
     * 결제 진행 결제 확인및 검증 결제 취소 및 환불 처리
     */

    private final PaymentRepository paymentRepository;
    private final UserProfileRepository userProfileRepository;


    public PaymentDTO createPayment(String userName, Integer totalPrice, Long orderId) {
        //유저네임으로 유저 프로파일 리파지토리에서 찾아서
        //유저가 가진 적립금이랑 토탈 금액이랑 비교
        //비교후 결제 완료
        //결제 완료 시 주문과 맵핑
        return null;

    }

    /*public PaymentDTO getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPayTotalPrice(payment.getPayTotalPrice());
        paymentDTO.setPayCreatedAt(payment.getPayCreatedAt());
        paymentDTO.setPayModifiedAt(payment.getPayModifiedAt());
        paymentDTO.setPayStatus(String.valueOf(payment.getPayStatus()));

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
    }*/
}
