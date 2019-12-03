package cn.laraboot.foundation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

public class App implements ApplicationContextAware {

    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        App.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    public static class BeanRegister {
        /**
         * 主动向Spring容器中注册bean
         *
         * @param name  BeanName
         * @param clazz 注册的bean的类性
         * @param args  构造方法的必要参数，顺序和类型要求和clazz中定义的一致
         * @param <T>
         * @return 返回注册到容器中的bean对象
         */
        public static <T> T bind(String name, Class<T> clazz, Object... args) {
            ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) getApplicationContext();
            if (applicationContext.containsBean(name)) {
                Object bean = applicationContext.getBean(name);
                if (bean.getClass().isAssignableFrom(clazz)) {
                    return (T) bean;
                } else {
                    throw new RuntimeException("BeanName 重复 " + name);
                }
            }


            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
            for (Object arg : args) {
                beanDefinitionBuilder.addConstructorArgValue(arg);
            }
            BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();

            BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
            beanFactory.registerBeanDefinition(name, beanDefinition);
            return applicationContext.getBean(name, clazz);
        }
    }
}
