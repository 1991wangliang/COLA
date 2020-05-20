#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Query;
import ${package}.dto.domainmodel.Customer;
import lombok.Data;

@Data
public class CustomerListByNameQry extends Query<MultiResponse<Customer>>{
   private String name;
}