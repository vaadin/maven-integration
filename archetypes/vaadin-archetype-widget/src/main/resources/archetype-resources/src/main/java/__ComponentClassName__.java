/* 
 * Copyright 2013 Joonas Lehtinen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vaadin.todolist;

import org.vaadin.todolist.client.TodolistClientRpc;
import org.vaadin.todolist.client.TodolistServerRpc;
import org.vaadin.todolist.client.TodolistState;

import com.vaadin.shared.MouseEventDetails;

// This is the server-side UI component that provides public API 
// for the users of the component
public class Todolist extends com.vaadin.ui.AbstractComponent {

	private int clickCount = 0;

	// To process events from the client, we implement ServerRpc
	private TodolistServerRpc rpc = new TodolistServerRpc() {

		// Event received from client - user clicked our widget
		public void clicked(MouseEventDetails mouseDetails) {
			
			// Send nag message every 5:th click with ClientRpc
			if (++clickCount % 5 == 0) {
				getRpcProxy(TodolistClientRpc.class)
						.alert("Ok, that's enough!");
			}
			
			// Update shared state. This state update is automatically 
			// sent to the client. 
			getState().text = "You have clicked " + clickCount + " times";
		}
	};

	public Todolist() {

		// To receive events from the client, we register ServerRpc
		registerRpc(rpc);
	}

	// We must override getState() to cast the state to TodolistState
	@Override
	public TodolistState getState() {
		return (TodolistState) super.getState();
	}
}
