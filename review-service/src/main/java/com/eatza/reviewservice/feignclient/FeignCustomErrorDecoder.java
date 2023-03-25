package com.eatza.reviewservice.feignclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignCustomErrorDecoder implements ErrorDecoder {
	private static final Logger log = LoggerFactory.getLogger(FeignCustomErrorDecoder.class);
	private final ErrorDecoder defaultErrorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {

		switch (response.status()) {
		case 404: ;
			return defaultErrorDecoder.decode(methodKey, response);
		default:
			break;
		}
		return defaultErrorDecoder.decode(methodKey, response);
	}

}
