package com.infinite.life.thread;


public class Question {

    Product  product=new Product();

    public class Product{
        private Object featureOne;
        private Object featureTwo;

        public void setter(Object featureOne,Object featureTwo){
            setFeatureOne(featureOne);
            setFeatureTwo(featureTwo);
        }

        private void setFeatureOne(Object featureOne) {
            this.featureOne = featureOne;
        }

        private void setFeatureTwo(Object featureTwo) {
            this.featureTwo = featureTwo;
        }

    }

    public class Requirement{
        void doString(){

        }
    }

    public class Programer{
        void doString(){

        }
    }
    public class Tester{
        void doString(){

        }
    }

    public class Strategy{
        Function function;

        public void setFunction(Function function) {
            this.function = function;
        }
    }

    public abstract class Function{
        abstract void setter(Product product);
    }
    public class FunctionOne extends Function{
        @Override
        synchronized void setter(Product product) {
            product.setter(new Object(),new Object());
        }
    }
    public class FunctionTwo extends Function{
        @Override
        synchronized void setter(Product product) {
            new Requirement().doString();
            new Programer().doString();
            new Tester().doString();
            product.setter(new Object(),new Object());
        }
    }
    /**
     * @description     如果每个人主人翁意识都很强，都认为自己是对的，然后选用策略1，最后开发出来的产品是开发人员想要的，还是需求想要的
     * @autohr cuiwx  2019/9/12
     * @return
     */
    public Product modify(){

        Strategy strategy=new Strategy();
        FunctionOne functionOne=new FunctionOne();
        FunctionTwo functionTwo=new FunctionTwo();
        strategy.setFunction(functionOne);
        for(int i=0;i<10;i++){
            new Thread(() -> strategy.function.setter(product)).start();

        }
//        strategy.setFunction(functionTwo);
//        strategy.function.setter(product);
        return product;
    }
}
