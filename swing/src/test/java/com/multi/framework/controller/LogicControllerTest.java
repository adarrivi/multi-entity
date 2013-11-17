package com.multi.framework.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.multi.swing.config.SpringApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringApplicationContext.class })
public class LogicControllerTest {

	@Autowired
	private LogicController victim;

	@Test
	public void getDefaultConfiguration() {
	}

}
