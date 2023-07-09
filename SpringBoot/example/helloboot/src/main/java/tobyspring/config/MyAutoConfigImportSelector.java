package tobyspring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();
        //container가 bean을 초기화 하면서 그 bean을 주입
//        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(candidate ->autoConfigs.add(candidate));
        //일단 후보를 가져오자, file 가져올 땐 classLoader
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

        // 자동으로 사용할 Configuration의 목록이 들어있음
        return autoConfigs.toArray(new String[0]);
        // return autoConfigs.stream().toArray(String[]::new);
        // return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(),String[].class)
    }
}
