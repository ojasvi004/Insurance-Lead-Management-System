package DataObjects;

public class PolicyFactory {
	BasePolicyDO createPolicy(String className) {
        if (className==null) {
            return null;
        }
        if (className.equals("TermInsuranceDO")) {
            return new TermInsuranceDO();
        } else if (className.equals("HealthInsuranceDO")) {
            return new HealthInsuranceDO();
        } else if (className.equals("InvestmentPlusDO")) {
            return new InvestmentPlusDO();
        } else {
            return null;
        }
    }
}