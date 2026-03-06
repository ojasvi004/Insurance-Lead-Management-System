package DataObjects;

import java.util.List;

public class PremiumCalculation {
	public double premiumCalTermIns(TermInsuranceDO termPolicy) {
		double sumAssured = termPolicy.getSumAssured();
		double riderPremium = 0.0;
		List<RiderDO> riders = termPolicy.getRiderDOList();
		if (riders != null) {
            for (RiderDO rider : riders) {
                riderPremium += (rider.getCoverAmount()*0.002);
            }
        }
		double totalMonthlyPremium = sumAssured * 0.0002 +riderPremium;
		return calculateModalPremium(totalMonthlyPremium, termPolicy.getPaymentFrequency());
	}
	public double premiumCalTermHealthIns(HealthInsuranceDO healthPolicy) {
		double sumAssured = healthPolicy.getSumAssured();
		double riderPremium = 0.0;
		List<RiderDO> riders = healthPolicy.getRiderDOList();
		if (riders != null) {
            for (RiderDO rider : riders) {
                riderPremium += (rider.getCoverAmount()*0.002);
            }
        }
		double totalMonthlyPremium = sumAssured * 0.001 +riderPremium;
		return calculateModalPremium(totalMonthlyPremium, healthPolicy.getPaymentFrequency());
	}
	private double calculateModalPremium(double monthlyPremium, String frequency) {
        if (frequency == null) {
            return monthlyPremium;
        }
        String freq = frequency.trim().toLowerCase();
        if (freq.equals("annually")) {
            return monthlyPremium * 12;
        } else if (freq.equals("semiannually")) {
            return monthlyPremium * 6;
        } else if (freq.equals("quarterly")) {
            return monthlyPremium * 3;
        } else {
            return monthlyPremium * 1;
        }
    }
	}
