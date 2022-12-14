package client.view.applicant.editUserProfile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;


public  class QualitiesListView {
    private ArrayList<QualityCell> qualityCells;
    private List<QualityCell> list = new ArrayList<>();

    public QualitiesListView(){
    }


    public BorderPane createCheckedList(String title, ArrayList<String> allQualities, ArrayList<String> applicantQualities) {
        BorderPane layout = new BorderPane();
        list = new ArrayList<>();
        qualityCells = new ArrayList<>();
        createList(allQualities);
        selectApplicantChoice(applicantQualities);

        ListView<QualityCell> listView = new ListView<QualityCell>();
        ObservableList<QualityCell> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);

       layout.setTop(new Label(title));
       layout.setCenter(listView);
       return layout;
    }
    private void createList(ArrayList<String> allQualities){
        if(allQualities !=null){
            System.out.println("All Qualities NOT NULL");
            for (int i = 0; i < allQualities.size(); i++) {
                QualityCell nextCell = new QualityCell(allQualities.get(i));
                list.add(nextCell);
            }
        }else{
            System.out.println("All Qualities NULL");
        }
    }
    private void selectApplicantChoice(ArrayList<String> applicantQualities){
        if(applicantQualities != null) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < applicantQualities.size(); j++) {
                    if (list.get(i).getLabel().equals(applicantQualities.get(j))) {
                        list.get(i).setCheckBox(true);
                        break;
                    }
                }

            }
        }
    }

    public void setEditable(boolean isEditable){
        for(int i = 0; i < qualityCells.size(); i++){
            qualityCells.get(i).setEditable(isEditable);
        }

    }
    public ArrayList<String> getPickedQualities(){
        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i < qualityCells.size(); i++){
            if(qualityCells.get(i).isChecked()){
                temp.add(qualityCells.get(i).getLabel());
            }
        }
        return temp;
    }

    public class QualityCell extends HBox {

        Label label = new Label();
        CheckBox checkBox = new CheckBox();


        QualityCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);
            checkBox.setDisable(true);
            qualityCells.add(this);
            this.getChildren().addAll(label, checkBox);
        }
        public boolean isChecked(){
            return checkBox.isSelected();
        }
        public void setEditable(boolean isEditable){
            checkBox.setDisable(!isEditable);
        }
        public String getLabel(){
            return label.getText();
        }
        public void setCheckBox(boolean isSelected){
            checkBox.setSelected(isSelected);
        }
    }

}
