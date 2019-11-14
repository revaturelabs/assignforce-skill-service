package com.revature.assignforce.testing;


import com.revature.assignforce.config.MethodSecurityConfig;
import com.revature.assignforce.controllers.SkillController;
import com.revature.assignforce.services.SkillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SkillController.class, SkillService.class, MethodSecurityConfig.class,
        Security.class})//pattern me
@WebMvcTest(SkillController.class)//pattern me

public class SkillControllerSecTest {
    @Configuration
    static class SkillServiceTestContextConfiguration {

        @Bean
        public SkillService SkillService() {
            return Mockito.mock(SkillService.class);
        }
    }//pattern me

    @Autowired
    MockMvc mvc;


    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldDenyAnonymousUser() throws Exception {
        mvc.perform(get("/")) //change my path
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles={"Manager of Technology"}) //sometimes change my role
    public void shouldAllowAuthenticatedUser() throws  Exception {
        mvc.perform(get("/")) //change my path
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles={"NERDS"})
    public void rongUser() throws  Exception {
        mvc.perform(get("/")) //change my path
                .andExpect(status().isForbidden());
    }

}
