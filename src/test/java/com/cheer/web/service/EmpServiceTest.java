package com.cheer.web.service;

import com.cheer.web.domain.Emp;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EmpServiceTest {

    @Test
    public void getList(){
        EmpService empService=new EmpService();
        List<Emp> list=empService.getList();
        for (Emp empp:list){
            System.out.println(empp);
        }
    }
}