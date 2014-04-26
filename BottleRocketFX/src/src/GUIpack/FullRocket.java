package src.GUIpack;

import java.io.Serializable;

import javafx.scene.Group;

public class FullRocket implements Serializable {
Group g;
AngularLaunch a;
public FullRocket(Group b, AngularLaunch c)
{
	g = b;
	a = c;
}

public Group getDrawing()
{
	return g;
}

public AngularLaunch getMath()
{
	return a;
}
}
