package com.customerinformation.project.controller;

import com.customerinformation.project.dao.CustormerInformationRepository;
import com.customerinformation.project.entity.CustomerInformationEntity;
import com.customerinformation.project.service.CustomerInformationServiceImpl;
import com.customerinformation.project.util.ApplicationConstantUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerInformationContoller.class)
class CustomerInformationContollerTest {

    @InjectMocks
    private CustomerInformationContoller theCustomerInformationContoller;

    @MockBean
    private CustormerInformationRepository theCustormerInformationRepository;

    @MockBean
    private CustomerInformationServiceImpl theCustomerInformationServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(theCustomerInformationContoller).build();
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 400})
    void save(int testStatus) throws Exception {
        CustomerInformationEntity testData = null;
        if(testStatus == 200) {
            testData = new CustomerInformationEntity();
            testData.setId(1);
            testData.setName("Amil");
            testData.setAge("24");
        }

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testData));

        mockMvc.perform(request).andExpect(status().is(testStatus));
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 400})
    void update(int testStatus) throws Exception {
        CustomerInformationEntity testData = null;
        if(testStatus == 200) {
            testData = new CustomerInformationEntity();
            testData.setId(1);
            testData.setName("Amil");
            testData.setAge("24");

            when(theCustomerInformationServiceImpl.findCustomerById("1"))
                    .thenReturn(Optional.of(testData));
            when(theCustomerInformationServiceImpl.update("1", testData))
                    .thenReturn(ApplicationConstantUtil.OK);
        }

        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/update")
                .param("Id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testData));

        mockMvc.perform(request).andExpect(status().is(testStatus));
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 400, 404})
    void getById(int testStatus) throws Exception {
        CustomerInformationEntity testData = new CustomerInformationEntity();
        testData.setId(1);
        testData.setName("Amil");
        testData.setAge("24");

        RequestBuilder request = null;
        if(testStatus == 200) {
            when(theCustomerInformationServiceImpl.findCustomerById(anyString())).thenReturn(Optional.of(testData));
            request = MockMvcRequestBuilders.get("/api/getCustomerById").param("Id","1");
        }else if(testStatus == 400) {
            request = MockMvcRequestBuilders.get("/api/getCustomerById");
        }else if(testStatus == 404) {
            request = MockMvcRequestBuilders.get("/api/getCustomerById").param("Id","1");
        }
        assertNotNull(request);
        mockMvc.perform(request).andExpect(status().is(testStatus));
    }

    @Test
    void getAll() throws Exception {
        List<CustomerInformationEntity> data = new ArrayList<CustomerInformationEntity>();
        CustomerInformationEntity testData1 = new CustomerInformationEntity();
        testData1.setId(1);
        testData1.setName("Amil");
        testData1.setAge("24");
        CustomerInformationEntity testData2 = new CustomerInformationEntity();
        testData1.setId(2);
        testData1.setName("Isang");
        testData1.setAge("24");

        data.add(testData1);
        data.add(testData2);

        when(theCustormerInformationRepository.findAll()).thenReturn(data);

        RequestBuilder request = MockMvcRequestBuilders.get("/api/getAll");
        mockMvc.perform(request).andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 400, 404})
    void deleteCustomerById(int testStatus) throws Exception {
        RequestBuilder request = null;
        if(testStatus == 200) {
            when(theCustomerInformationServiceImpl.delete(anyString())).thenReturn(ApplicationConstantUtil.OK);
            request = MockMvcRequestBuilders
                    .delete("/api/deleteCustomerById")
                    .param("Id", "1");
        }else if(testStatus == 404) {
            when(theCustomerInformationServiceImpl.delete(anyString())).thenReturn(ApplicationConstantUtil.NOT_FOUND);
            request = MockMvcRequestBuilders
                    .delete("/api/deleteCustomerById")
                    .param("Id", "1");
        } else if(testStatus == 400) {
            request = MockMvcRequestBuilders
                    .delete("/api/deleteCustomerById");
        }


        mockMvc.perform(request).andExpect(status().is(testStatus));
    }
}