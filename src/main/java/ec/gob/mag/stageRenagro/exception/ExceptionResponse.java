package ec.gob.mag.stageRenagro.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {
	private HttpStatus status;
	private Integer statusCode;
	private Date timestamp;
	private String message;
	private String details;
	private String proyect;
	private String classe;
	private String method;
	private EnumCodeExceptions numberCode;
	private EnumTypeExceptions type;
}
