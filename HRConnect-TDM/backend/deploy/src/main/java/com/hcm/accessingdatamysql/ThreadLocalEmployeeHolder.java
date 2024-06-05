import org.springframework.stereotype.Component;

import com.hcm.accessingdatamysql.entity.employee;

@Component
public class ThreadLocalEmployeeHolder {

    private static final ThreadLocal<employee> threadLocalEmployee = new ThreadLocal<>();

    public static Employee getEmployee() {
        return threadLocalEmployee.get();
    }

    public static void setEmployee(employee emp) {
        threadLocalEmployee.set(emp);
    }

    public static void removeEmployee() {
        threadLocalEmployee.remove();
    }
}
