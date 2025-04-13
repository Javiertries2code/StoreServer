package com.elorrieta.storeapi.helpers;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrintDebug {

	public void pM(String loc, String message) {
		
		System.out.println(loc);
		System.out.println(message);

		
	}
	
public void pM(String loc, int  message) {
		
		System.out.println(loc);
		System.out.println(message);

		
	}
	
	
}
