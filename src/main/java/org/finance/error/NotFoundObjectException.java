package org.finance.error;

public class NotFoundObjectException extends RuntimeException{
        private final String objectClazz;
        private final String id;

        public  NotFoundObjectException(String objectClazz, String id, String message){
            super(message);
            this.objectClazz = objectClazz;
            this.id = id;
        }
}
