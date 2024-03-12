package springboot.boostrap.cnt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springboot.boostrap.model.WeightInfo;
import springboot.boostrap.service.WeightInfoService;

@RestController
public class WeightViewRestCnt {
    //JSONによる一覧表示
    @Autowired
    private WeightInfoService service;

    @GetMapping("/info")
    public List<WeightInfo> getAllData() {
        return service.findAll();
    }

    @PutMapping("/info")
    public ModelAndView regist(WeightInfo weightInfo, RedirectAttributes attributes,
    		@RequestParam(name = "registYear", required = false) Integer years,
    		@RequestParam(name = "registMonth", required = false) Integer months) {
        attributes.addFlashAttribute("weightInfo", weightInfo);
        
		List<WeightInfo> data = service.searchmonth(years, months);
		attributes.addFlashAttribute("data", data);
        attributes.addFlashAttribute("month", months);
        
        //登録する
        service.regist(weightInfo);
        //入力画面に戻す
        var view = new ModelAndView();
        view.setViewName("redirect:/");
        return view;
    }

    @PutMapping("/infob")
    public List<WeightInfo> regist_b(WeightInfo weightInfo) {
        //登録する
        service.regist(weightInfo);
        //データを一覧表示(JSON)
        return getAllData();
    }

    @GetMapping("/searchInfo")
    public List<WeightInfo> searchYearMonth(
            @RequestParam(name = "searchYear") Integer year,
            @RequestParam(name = "searchMonth") Integer month,
            @RequestParam(name = "searchDay") Integer day) {
        return service.search(year, month, day);
    }

    @PutMapping("/update")
    public String update(WeightInfo weightInfo) {
        service.update(weightInfo);//JSONで表示
        return "redirect:/";
    }

    @DeleteMapping("/update/{id}")
    public List<WeightInfo> delete(@PathVariable("id") int id) {
        service.delete(id);
        return getAllData();
    }
}
