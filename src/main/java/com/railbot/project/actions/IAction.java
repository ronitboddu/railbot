package com.railbot.project.actions;

import java.sql.SQLException;

import com.railbot.project.models.Request;
import com.railbot.project.models.Response;

public interface IAction {
	public Response performAction(Request request) throws ClassNotFoundException, SQLException;
}
