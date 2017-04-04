package uo.asw.dashboard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DashboardSuggestionControllerREST {

    @RequestMapping("suggestions/{id}")
    public @ResponseBody ResponseEntity<Map<String, Object>> getSuggestion(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
    }
}
