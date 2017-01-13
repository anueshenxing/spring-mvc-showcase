package org.springframework.samples.mvc.zhang_plot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 张晓磊 on 2017/1/12.
 */
@Controller
public class PlotController {
    @RequestMapping(value="plot/plot_test",method = RequestMethod.GET)
    public String mongoTest(Model model){
        return "plot/plot_complete_network";
    }

    @ResponseBody
    @RequestMapping(value="plot/get_webkit_dep",method = RequestMethod.GET)
    public String getData(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("E:\\share\\webkei_dep.json"));// 读取原始json文件
            try {
                String s = br.readLine();
                return s;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
