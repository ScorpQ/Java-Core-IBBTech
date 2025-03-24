package P1_PROJECT.dto;

/*
 * Record yapısında 'Inheritance' yapısı kullanılmaz.
 * Fakat 'Composition' yapısı ile 'GuardDTO' class'ı 'HumanDTO' class'ını extend edebiliriz.
 * 
 * 
 */
public record GuardDTO(
    HumanDTO human,
    String guardType,
    int yearsOfService,
    int salary,
    boolean isOfficer
) {
    
    public GuardDTO {
        if(human == null) throw new IllegalArgumentException("Human cannot be null");
        if(guardType == null || guardType.isEmpty() || guardType.isBlank()) throw new IllegalArgumentException("Guard type cannot be null or empty");
        if(yearsOfService < 0) throw new IllegalArgumentException("Years of service cannot be negative");
        if(salary < 0) throw new IllegalArgumentException("Salary cannot be negative");
    }

    
}
