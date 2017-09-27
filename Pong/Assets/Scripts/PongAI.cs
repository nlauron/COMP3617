using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PongAI : MonoBehaviour {

    public Transform ball;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		if (ball.transform.position.y > transform.position.y)
        {
            transform.Translate(0, .1f, 0);
        }

        if (ball.transform.position.y < transform.position.y)
        {
            transform.Translate(0, -.1f, 0);
        }
        
    }
}
