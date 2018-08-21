package presentation.user.generator;

import business.group.Group;
import business.user.User;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

public class TableGroupGenerator implements Table.ColumnGenerator {

    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {
        Label label = new Label();
        StringBuilder labelContent = new StringBuilder();
        for(Group group : ((User) itemId).getGroup()){
            labelContent.append(group.getGroupName()).append(",");
        }
        label.setValue(labelContent.toString());
        return label;
    }
}
