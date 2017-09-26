using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ball : MonoBehaviour {

    public float force = 0.0f;

	// Use this for initialization
	void Start () {
        GetComponent<Rigidbody>().AddForce(Vector3.right * force);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    private void blueCollision(Collision collision)
    {
        if (collision.gameObject.tag == "Blue")
            BlueScore.score += 1;
    }

    private void redCollision(Collision collision)
    {
        if (collision.gameObject.tag == "Red")
            RedScore.score += 1;
    }
}
