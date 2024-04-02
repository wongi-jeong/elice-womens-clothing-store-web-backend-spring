package cloud2.shopingmall.common.exception;

public class OrderException{
    public static class OrderNotFoundException extends RuntimeException{
       public OrderNotFoundException(Long id){
           super("order with id " +id +"not found");
       }
    }
    public static class OrderCancellationNotAllowedException extends RuntimeException{
        public OrderCancellationNotAllowedException(Long id){
            super("order with id "+id +"is NOT Allowed cancel");
        }

    }

    public static class OrderNotFoundOrderProductException extends RuntimeException{
        public OrderNotFoundOrderProductException(){
            super("주문하실 상품이 없습니다.");
        }
    }
}
