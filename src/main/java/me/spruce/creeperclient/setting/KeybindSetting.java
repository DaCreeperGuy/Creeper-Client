package me.spruce.creeperclient.setting;

public class KeybindSetting extends SettingOld {
	
	public int code;
	
	public KeybindSetting(int code) {
		this.name = "Keybind";
		this.code = code;
		
	}

	public int getKeyCode() {
		return code;
	}

	public void setKeyCode(int code) {
		this.code = code;
	}

}
