



package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pojo_BodyDummy {

    //{
    //"status": "success",
    //"data": {
    //"id": 3,
    //"employee_name": "Ashton Cox",
    //"employee_salary": 86000,
    //"employee_age": 66,
    //"profile_image": ""
    //},
    //"message": "Successfully! Record has been fetched."
    //}

    private String status;
    private Pojo_DataDummy dataDummy;

    private String message;
}
