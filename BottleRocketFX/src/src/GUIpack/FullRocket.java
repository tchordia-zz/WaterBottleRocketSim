package src.GUIpack;

import java.io.Serializable;

import mathPack.AngularLaunch;
import javafx.scene.Group;

public class FullRocket implements Serializable {
CreateRocket g = null;
AngularLaunch a = null;
ProjectileBall b = null;

public FullRocket()
{
}

public FullRocket(CreateRocket r, ProjectileBall p, AngularLaunch a)
{
	b = p;
	g = r;
}
public void update( CreateRocket r)
{
	g = r;
}
public void update( AngularLaunch r)
{
	a = r;
}
public void update( ProjectileBall r)
{
	b = r;
}
public CreateRocket getCreateRocket()
{
	return g;
}

public AngularLaunch getAngularLaunch()
{
	return a;
}

public ProjectileBall getProjectileBall()
{
	return b;
}

public FullRocket copy()
{
	return new FullRocket(g,b,a);
}
}
