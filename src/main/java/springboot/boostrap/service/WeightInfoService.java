package springboot.boostrap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.boostrap.model.WeightInfo;

@Service
public class WeightInfoService {
    @Autowired
    private WeightInfoRepository repository;

    public List<WeightInfo> findAll() {
        return repository.findAll();
    }

    public List<WeightInfo> search(int year, int month, int day) {
        return repository.findYearMonth(year, month, day);
    }

    public List<WeightInfo> searchmonth(int year, int month) {
        return repository.findYearMonthNoDay(year, month);
    }

    public WeightInfo regist(WeightInfo weightInfo) {
        weightInfo.setId(0);
        return repository.save(weightInfo);
    }

    public WeightInfo update(WeightInfo weightInfo) {
        return repository.save(weightInfo);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
