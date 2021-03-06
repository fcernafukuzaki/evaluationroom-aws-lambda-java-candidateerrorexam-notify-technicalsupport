package pe.evaluationroom.candidateerrorexam.notify.technicalsupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

import pe.evaluationroom.candidateerrorexam.notify.technicalsupport.bean.SoporteTecnicoNotificacion;
import pe.evaluationroom.candidateerrorexam.notify.technicalsupport.bean.Detalle;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class SNSPublisherHandler implements RequestHandler<SoporteTecnicoNotificacion, APIGatewayProxyResponseEvent> {

	static final Logger logger = LogManager.getLogger(SNSPublisherHandler.class);
	
	private static final String TOPIC_ARN_EMAIL = "arn:aws:sns:us-east-1:305121837514:EvaluationRoom_CandidateErrorExam-Notify-TechnicalSupport";
	
	private static AmazonSNSClient snsClient = null;
	
	private String getEmailRecruiterSubject(String correoElectronico, String observacion, Detalle detalle) {
		return (new StringBuffer("El candidato ")).
				append(correoElectronico).
				append(" ha notificado el error ").
				append(observacion).
				append(" ").
				append(new String("\ud83d\ude28")).
				toString();
	}
	
	private String getEmailRecruiterMessage(String correoElectronico, String observacion, Detalle detalle) {
		String detalleError = detalle != null ? (
				new StringBuffer("Detalle del error:").
				append("\n").
				append(detalle.getMensaje()).
				append("\n\n")).
				toString() : "";
		
		return (new StringBuffer("El candidato ")).
				append(correoElectronico).
				append(" tiene un error.").
				append("\n\n").
				append("Error notificado: ").
				append(observacion).
				append("\n\n").
				append(detalleError).
				append("Mensaje automatizado de EvaluationRoom.").
				toString();
	}
	
	@Override
	public APIGatewayProxyResponseEvent handleRequest(SoporteTecnicoNotificacion input, Context context) {
		logger.info("[INICIO]" + input);
		
		// crear cliente SNS
		snsClient = (AmazonSNSClient) AmazonSNSClientBuilder.standard().build();
		snsClient.publish(TOPIC_ARN_EMAIL, 
				getEmailRecruiterMessage(input.getCorreoElectronico(), input.getObservacion(), input.getDetalle()), 
				getEmailRecruiterSubject(input.getCorreoElectronico(), input.getObservacion(), input.getDetalle()));
		
		String output = "Sent.";
		
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		responseEvent.setStatusCode(200);
		responseEvent.setBody(output);
		return responseEvent;
	}
	
}
