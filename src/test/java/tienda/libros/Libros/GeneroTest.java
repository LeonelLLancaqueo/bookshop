package tienda.libros.Libros;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

// import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.json.JSONArray;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



import static org.junit.jupiter.api.Assertions.assertEquals;



@AutoConfigureMockMvc
@SpringBootTest
public class GeneroTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void generoTest () throws Exception{
        MvcResult result=
        mockMvc.perform(get("/genero"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();


        JSONArray jsonArray= new JSONArray(result.getResponse().getContentAsString());
        
        assertEquals(jsonArray.length(), 1);
        
        
        
    }

    


}
