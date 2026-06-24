package com.edificio.reserva;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservaApplication {

	private static final Logger logger =
			LoggerFactory.getLogger(ReservaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReservaApplication.class, args);
		logger.info("Microservicio Reservas iniciado correctamente");
	}

}
