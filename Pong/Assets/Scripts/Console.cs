using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Console : MonoBehaviour {

    public GameObject inputField;
    public InputField console;
    public Camera cam;
    public static bool toggleBool = false;

	// Use this for initialization
	void Start () {
        inputField.SetActive(toggleBool);
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown("c")) {
            toggleBool = !toggleBool;
            inputField.SetActive(toggleBool);
        }

        if (Input.GetKeyDown("return"))
        {
            if (console.text.Equals("bgMagenta"))
                cam.backgroundColor = Color.magenta;

            if (console.text.Equals("bgNormal"))
                cam.backgroundColor = Color.black;

            if (console.text.Equals("spdup"))
                Ball.speed = Ball.speed + 5f;

            if (console.text.Equals("spddown") && Ball.speed != 5f)
                Ball.speed = Ball.speed - 5f;
            toggleBool = false;
            inputField.SetActive(toggleBool);
        }
	}
}
