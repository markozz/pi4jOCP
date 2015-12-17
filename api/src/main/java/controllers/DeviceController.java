package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/device")
public class DeviceController {

    @RequestMapping(value = "/{device}", method = RequestMethod.GET)
    public String getState(@PathVariable String device, ModelMap model) {

        model.addAttribute("deviceState","state of device "+device+" is off");
        return "device";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String baseDir(ModelMap model) {

        model.addAttribute("deviceState","please add a device id to the url");
        return "device";
    }
}
