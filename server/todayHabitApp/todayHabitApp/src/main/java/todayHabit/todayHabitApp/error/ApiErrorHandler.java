package todayHabit.todayHabitApp.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiErrorHandler extends Exception {

    /*
    * ClassService 에러
    * */
    @ExceptionHandler(value = ReserveBlockException.class)
    public ResponseEntity<Object> ReserveBlockException(ReserveBlockException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException("예약이 차단된 날짜입니다.", httpStatus, LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    /*
     * MemberService 에러
     * */
    @ExceptionHandler(value = NonExistGymException.class)
    public ResponseEntity<Object> NonExistGymException(NonExistGymException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException("존재하지 않는 센터입니다.", httpStatus, LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = AlreadyRegisterException.class)
    public ResponseEntity<Object> AlreadyRegisterException(AlreadyRegisterException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException("이미 등록된 센터입니다.", httpStatus, LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = NotExistMemberException.class)
    public ResponseEntity<Object> NotExistMemberException(NotExistMemberException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException("존재하지 않는 회원입니다.", httpStatus, LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = NotCorrectPasswdException.class)
    public ResponseEntity<Object> NotCorrectPasswdException(NotCorrectPasswdException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException("비밀번호가 틀렸습니다.", httpStatus, LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }
    
    @ExceptionHandler(value = AlreadyExistMemberException.class)
    public ResponseEntity<Object> AlreadyExistMemberException(AlreadyExistMemberException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException("이미 존재하는 회원입니다.", httpStatus, LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }
    
    /*
    * ClassService 에러 핸들러
    * */

    @ExceptionHandler(value = TimeoutReserveException.class)
    public ResponseEntity<Object> TimeoutReserveException(TimeoutReserveException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException("예약 가능 시간이 지났습니다.", httpStatus, LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = MaxClassException.class)
    public ResponseEntity<Object> MaxClassException(TimeoutReserveException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException("예약 정원이 다찼습니다.", httpStatus, LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = AlreadyReserveClassException.class)
    public ResponseEntity<Object> AlreadyReserveClassException(AlreadyReserveClassException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException("이미 예약 중인 회원입니다.", httpStatus, LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }


    @Data
    @AllArgsConstructor
    static class ApiException {

        private final String message;
        private final HttpStatus httpStatus;
        private final LocalDateTime time;


    }

}


