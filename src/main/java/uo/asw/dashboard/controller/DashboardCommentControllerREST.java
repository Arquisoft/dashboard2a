package uo.asw.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uo.asw.dashboard.GetComments;
import uo.asw.dbmanagement.model.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class DashboardCommentControllerREST {
    @Autowired
    private GetComments commentsService;

    @CrossOrigin(origins = "*")
    @RequestMapping("/comments/{id}")
    public @ResponseBody ResponseEntity<Map<String, Object>> getComment(@PathVariable(value = "id") String id){
        Long idL;
        try {
            idL = Long.parseLong(id);
        }catch (NumberFormatException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Comment c = commentsService.getCommentById(idL);
        if(c == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            Map<String, Object> ret = c.toMap();
            return new ResponseEntity<>(ret , null, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/comments")
    public @ResponseBody ResponseEntity<List<Map<String, Object>>> getComents(){
        List<Map<String, Object>> ret = new ArrayList<>();
        for (Comment c: commentsService.getComments()
             ) {
            ret.add(c.toMap());
        }
        return new ResponseEntity<>(ret, null, HttpStatus.OK);
    }

}
