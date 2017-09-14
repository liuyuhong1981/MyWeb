package org.lyh.myweb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

/**
 * 
 */

/**
 * @author liuyuho
 *
 */
public class MathTest {

    public static void mathTest2() {
        /**
         * 描述线性问题，添加方程式 define objective equation example: z = 160x + 180y +
         * 120z + 0 -> LinearObjectiveFunction f = new
         * LinearObjectiveFunction({160, 180, 120}, 0);
         */
        double[] objective = { 1, 0, 0, 0 };// z = x
        LinearObjectiveFunction f = new LinearObjectiveFunction(objective, 0);

        /**
         * 添加条件方程式 define constraint equation example: 2x + 4y + 2z <= 480 ->
         * constraints.add(new LinearConstraint(new double[] { 2, 4, 2},
         * Relationship.LEQ, 480));
         */
        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();

        /**
         * 
         * 
         * s = 5x + 3y 3x + 5y <= 37 x >= 1 y - x >= 1
         * 
         * z = 200 x + z = 180 y = 120
         */
        constraints.add(new LinearConstraint(new double[] { 1, 1, 0, 0 }, Relationship.EQ, 300));
        constraints.add(new LinearConstraint(new double[] { 0, 0, 1, 1 }, Relationship.EQ, 900));
        constraints.add(new LinearConstraint(new double[] { 1, 0, 1, 0 }, Relationship.EQ, 720));
        constraints.add(new LinearConstraint(new double[] { 0, 1, 0, 1 }, Relationship.EQ, 480));

        /**
         * 创建并运行solver
         */
        PointValuePair solution = null;
        solution = new SimplexSolver().optimize(f, new LinearConstraintSet(constraints), GoalType.MAXIMIZE, new NonNegativeConstraint(true));

        /**
         * 获取结果
         */
        if (solution != null) {
            double max = solution.getValue();
            System.out.println("Opt: " + max);

            // 结果打印
            for (int i = 0; i < objective.length; i++) {
                System.out.print(solution.getPoint()[i] + "\t");
            }
        }

    }

    static void mathTest1() {
        List<Double> values = Arrays.asList(100d, 50d, 50d);
        double[][] calMatrix = { { 1, 1, 0 }, { 1, 0, 0 }, { 0, 1, 0 } };

        LinearObjectiveFunction f = new LinearObjectiveFunction(calMatrix[0], 0);
        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
        for (int i = 0; i < values.size(); i++) {
            double[] entry = calMatrix[i];
            constraints.add(new LinearConstraint(entry, Relationship.EQ, values.get(i)));
        }
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(new MaxIter(1000), f, new LinearConstraintSet(constraints), GoalType.MAXIMIZE,
                new NonNegativeConstraint(true));
        double[] orderCountList = solution.getPoint();

        for (Double num : orderCountList) {
            System.out.print(num + ", ");
        }
    }

    public static void testMathAllocation() {
        List<String> list = new ArrayList<String>();
        list.add("0.4");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");

        // 取得配额总和
        double totalRatio = 0;
        int count = 100;
        for (String s : list) {
            totalRatio = totalRatio + Double.parseDouble(s);
        }

        // 技能组A：催收员1：1.0 催收员2：1.0 催收员3：0.8 催收员4：1.2
        //
        // 如果此次跑批分配100个合同技能组A，分配规则如下：
        // 100/(1.0+1.0+0.8+1.2)=25
        // 催收员1：1.0 *25=25
        // 催收员2：1.0 *25=25
        // 催收员3：0.8 *25= 20
        // 催收员4：1.2*25=30

        double basicCountTemp = count / totalRatio;
        Map<String, Integer> staffRatioMap = new HashMap<String, Integer>();
        int basicCount = (int) Math.floor(basicCountTemp);
        int realCount = 0;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            double howMany = basicCount * Double.parseDouble(s);
            staffRatioMap.put(String.valueOf(i), (int) Math.floor(howMany));
            realCount = realCount + (int) Math.floor(howMany);
        }

        int gap = count - realCount;
        if (gap > 0) {
            Set<String> ks = staffRatioMap.keySet();
            Iterator<String> it = ks.iterator();
            while (it.hasNext()) {
                String staffId = it.next();
                Integer howMany = staffRatioMap.get(staffId);
                howMany = howMany + 1;
                staffRatioMap.put(staffId, howMany);
                gap = gap - 1;
                if (gap == 0) {
                    break;
                }
            }
        }
        System.out.println(staffRatioMap);
    }

    public static void testMathMethod() {
        double totalRatio = 0;
        int count = 1000;
        double basicCountTemp = count / totalRatio;
        int basicCount = (int) Math.floor(basicCountTemp);

        System.out.println(basicCount);
    }

    public static int testDemo(int[] A) {
        double sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        double left = 0;
        for (int i = 0; i < A.length; i++) {
            int p = A[i];
            if (left * 2 == (sum - p)) {
                return i;
            }
            left += A[i];
        }
        return -1;
    }

}