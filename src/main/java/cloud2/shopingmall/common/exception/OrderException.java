package cloud2.shopingmall.common.exception;

public class OrderException{
    public static class OrderNotFoundExecption extends RuntimeException{
       public OrderNotFoundExecption(Long id){
           super("order with id " +id +"not found");
       }
    }
    public static class OrderCancellationNotAllowedException extends RuntimeException{
        public OrderCancellationNotAllowedException(Long id){
            super("order with id "+id +"is NOT Allowed cancel");
        }

    }
}
