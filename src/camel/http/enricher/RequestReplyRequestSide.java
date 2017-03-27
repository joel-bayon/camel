package camel.http.enricher;

import java.util.Random;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
	 
	public class RequestReplyRequestSide implements MessageListener {
	  
	    private MessageProducer producer;
	 
	    public RequestReplyRequestSide() {
	        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	        Connection connection;
	        try {
	            connection = connectionFactory.createConnection();
	            connection.start();
	            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	            Destination adminQueue = session.createQueue("request.messages");
	            //Destination replyDest = session.createQueue("reply.messages");
	 
	            //Setup a message producer to send message to the queue the server is consuming from
	            this.producer = session.createProducer(adminQueue);
	            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	 
	            //Create a temporary queue that this client will listen for responses on then create a consumer
	            //that consumes message from this temporary queue...for a real application a client should reuse
	            //the same temp queue for each message to the server...one temp queue per client
	            Destination tempDest = session.createTemporaryQueue();
	            
	            MessageConsumer responseConsumer = session.createConsumer(tempDest);
	 
	            //This class will handle the messages to the temp queue as well
	            responseConsumer.setMessageListener(this);
	            Scanner clavier = new Scanner(System.in);
	            while(true) {
		            //Now create the actual message you want to send
	            	System.out.print("Enter a firstname : ");
	            	String firstName = clavier.nextLine();
		            TextMessage txtMessage = session.createTextMessage();
		            txtMessage.setText(firstName);
		 
		            //Set the reply to field to the temp queue you created above, this is the queue the server
		            //will respond to
		            txtMessage.setJMSReplyTo(tempDest);
		 
		            //Set a correlation ID so when you get a response you know which sent message the response is for
		            //If there is never more than one outstanding message to the server then the
		            //same correlation ID can be used for all the messages...if there is more than one outstanding
		            //message to the server you would presumably want to associate the correlation ID with this
		            //message somehow...a Map works good
		            String correlationId = this.createRandomString();
		            txtMessage.setJMSCorrelationID(correlationId);
		            this.producer.send(txtMessage);
		            if("0".equals(firstName)) break;
	            }
	            clavier.close();
	            connection.close();
	        } catch (JMSException e) {
	            //Handle the exception appropriately
	        }
	    }
	    private String createRandomString() {
	        Random random = new Random(System.currentTimeMillis());
	        long randomLong = random.nextLong();
	        return Long.toHexString(randomLong);
	    }
	 
	    public void onMessage(Message message) {
	        String messageText = null;
	        try {
	            if (message instanceof TextMessage) {
	                TextMessage textMessage = (TextMessage) message;
	                messageText = textMessage.getText();
	                System.out.println("messageText = " + messageText);
	            }
	        } catch (JMSException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    public static void main(String[] args) {
	    	System.out.println("*** start the client ...");
	        new RequestReplyRequestSide();
	    }
	}
