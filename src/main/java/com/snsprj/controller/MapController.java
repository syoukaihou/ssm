package com.snsprj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MapController {

	@RequestMapping(value="map/onep",method={RequestMethod.GET})
	public String mapOnePoint(){
		
		return "jsps/map";
	}
	
	@RequestMapping(value="map/multiplep",method={RequestMethod.GET})
	public String mapMutiplePoint(){
		
		return "jsps/mapMultiplePoint";
	}
}
