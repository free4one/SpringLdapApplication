package freez.springldap;

import freez.springldap.config.WebSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

@SpringBootTest
@AutoConfigureMockMvc
@Import(WebSecurityConfig.class)
class SpringLdapApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
                .user("ben")
                .password("benspassword");

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("ben"));
    }

    @Test
    public void loginWithInvalidUserThenUnauthenticated() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
                .user("invalid")
                .password("invalidpassword");

        mockMvc.perform(login)
                .andExpect(unauthenticated());
    }

}
