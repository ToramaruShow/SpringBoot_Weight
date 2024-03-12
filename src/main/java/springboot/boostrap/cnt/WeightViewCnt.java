package springboot.boostrap.cnt;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import springboot.boostrap.model.WeightInfo;
import springboot.boostrap.service.WeightInfoService;

@Controller
public class WeightViewCnt {
	private final LocalDateTime ldt = LocalDateTime.now();
	private final int years = ldt.getYear();
	private final int months = ldt.getMonthValue();
	private final int days = ldt.getDayOfMonth();

	@Autowired
	private WeightInfoService service;

	@GetMapping("/")
	public String showInput(@ModelAttribute("weightInfo") WeightInfo registWeight, Model model) {
		var weightInfo = new WeightInfo(0, 0d, years, months, days);
		model.addAttribute(weightInfo);
		System.out.println(registWeight.getId());
		List<WeightInfo> data = null;
		if (Objects.equals(registWeight.getId(), 0)) {
			data = service.searchmonth(years, months);
			model.addAttribute("month", months);
		} else {
			data = service.searchmonth(registWeight.getRegistYear(), registWeight.getRegistMonth());
			model.addAttribute("month", registWeight.getRegistMonth());
		}
		StringBuilder sb_day = new StringBuilder(), sb_weight = new StringBuilder();
		for (WeightInfo d : data) {
			sb_day.append(d.getRegistDay()).append(",");
			sb_weight.append(d.getWeight()).append(",");
		}
		if (sb_day.length() != 0 && sb_weight.length() != 0) {
			sb_day.deleteCharAt(sb_day.length() - 1);
			sb_weight.deleteCharAt(sb_weight.length() - 1);
		}
		String day = sb_day.toString(), weight = sb_weight.toString();
		model.addAttribute("days", day);
		model.addAttribute("weights", weight);
		model.addAttribute("list", data);
		return "/weight_input";
	}

	@GetMapping("/search")
	public String showSearch(Model model, @RequestParam(name = "btn", required = false) String btn,
			@RequestParam(name = "searchYear", required = false) Integer searchYear,
			@RequestParam(name = "searchMonth", required = false) Integer searchMonth,
			@RequestParam(name = "btnnp", required = false) String btnnp) {
		var map = new HashMap<String, Integer>();
		map.clear();
		model.addAllAttributes(map);
		model.addAttribute("search", false);
		map.put("year", years);
		map.put("month", months);
		if (Objects.equals("search", btn) || Objects.equals("next", btnnp) || Objects.equals("prev", btnnp)) {
			model.addAttribute("search", true);
			model.addAttribute("list", service.searchmonth(searchYear, searchMonth));
			if (Objects.equals("next", btnnp)) {
				if (Objects.equals(searchMonth, 12)) {
					searchYear++;
					searchMonth = 1;
				} else {
					searchMonth++;
				}
			} else if (Objects.equals("prev", btnnp)) {
				if (Objects.equals(searchMonth, 1)) {
					searchYear--;
					searchMonth = 12;
				} else {
					searchMonth--;
				}
			}
			List<WeightInfo> list = service.searchmonth(searchYear, searchMonth);
			model.addAttribute("data", searchMonth);
			StringBuilder sb_day = new StringBuilder(), sb_weight = new StringBuilder();
			for (WeightInfo s : list) {
				sb_day.append(s.getRegistDay()).append(",");
				sb_weight.append(s.getWeight()).append(",");
			}
			if (sb_day.length() != 0 && sb_weight.length() != 0) {
				sb_day.deleteCharAt(sb_day.length() - 1);
				sb_weight.deleteCharAt(sb_weight.length() - 1);
			}
			String day = sb_day.toString(), weight = sb_weight.toString();
			//条件に合わせて検索
			model.addAttribute("days", day);
			model.addAttribute("weights", weight);
			map.put("year", searchYear);
			map.put("month", searchMonth);
		}
		map.put("day", days);
		model.addAllAttributes(map);
		return "/weight_search";
	}
}
