# How to make a new scene
****
### 1) Create .fxml file

![Scene Directory](../../../../../resources/images/sceneDir.png)

**Right-click the scenes directory in resource folder**

![img.png](../../../../../resources/images/newFXML.png)

**Select FXML file**

Name this file appropriately


### 2) Create a controller for your new scene

#### Add a new java file in the scenes package 

![img.png](../../../../../resources/images/controllerDir.png)

#### Make this new class extend the FXMLController Class

![img.png](../../../../../resources/images/extendFXMLController.png)

#### Set fxmlName():
Override the function "fxmlName()" and return a string of the .fxml file you previously created

### 3) Add your controller to your .fxml file

At the end of the first tag in the .fxml file add the package name of your new controller class

_fx:controller="com.se.se300_bed2bed.scenes.**controller_name**"_

![img_2.png](../../../../../resources/images/assignControllerToScene.png)

### 4) Finally add your controller class to the scene list in the

Within the Bed2BedApp class, add your new scene in the initScenes method

![img_3.png](../../../../../resources/images/addSceneClassToApp.png)
