package springboot.boostrap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "weight")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeightInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double weight;
    private int registYear;
    private int registMonth;
    private int registDay;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        var registDate = String.format("\t%04d-%02d-%02d\t", registYear, registMonth, registDay);
        builder.append(id);
        builder.append(registDate);
        builder.append(String.format("%.1f kg", weight));
        builder.append(weight);
        builder.append("]");
        return builder.toString();
    }
}
