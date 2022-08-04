package com.customerinformation.project.testControllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.customerinformation.project.controller.CustomerInformationContoller;
import com.customerinformation.project.dao.CustormerInformationRepository;
import com.customerinformation.project.entity.CustomerInformationEntity;

public class CustomerInformationControllerTest {


	@Test
	private void SaveTest() throws Exception {

	}
}
