package cc.lostyouth.spring.highlight_spring4.ch03.conditional;

/**
 * Created by endless on 11/6/2017.
 */
public class LinuxListService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
