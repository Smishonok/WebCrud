package org.valentinenikolaev.webcrud.utils.beans;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.valentinenikolaev.webcrud.controllers.filters.ConditionChecker;
import org.valentinenikolaev.webcrud.controllers.filters.userpreconditionscheckers.UserLoginChecker;
import org.valentinenikolaev.webcrud.controllers.filters.userpreconditionscheckers.UserRegParametersFulfillmentChecker;

@Configuration
@Import(RepositoryBeans.class)
public class FiltersBeans {



    public ConditionChecker userRegParametersChecker() {
        UserRegParametersFulfillmentChecker checker = new UserRegParametersFulfillmentChecker();
        checker.addNext(new UserLoginChecker());

        return
    }

}
