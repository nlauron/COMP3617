using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Ball : MonoBehaviour {

    public int changeScene = 3;
    public static float speed = 10f;
    public float sx;
    public float sy;
    public static string prevScene = "";

	// Use this for initialization
	void Start () {
        sx = Random.Range(0, 2) == 0 ? -1 : 1;
        sy = Random.Range(0, 2) == 0 ? -1 : 1;

        GetComponent<Rigidbody>().velocity = new Vector3(speed * sx, speed * sy, 0f);
    }
	
	// Update is called once per frame
	void Update () {
        if (BlueScore.score == 5 || RedScore.score == 5)
        {
            PlayerPrefs.SetString("lastloadedScene", SceneManager.GetActiveScene().name);
            SceneManager.LoadScene(changeScene);
        }
    }


    private void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.tag == "Blue")
        {
            RedScore.score += 1;
            transform.position = new Vector3(0, 1, 5);
            sx = Random.Range(0, 2) == 0 ? -1 : 1;
            sy = Random.Range(0, 2) == 0 ? -1 : 1;
            GetComponent<Rigidbody>().velocity = new Vector3(speed * sx, speed * sy, 0f);
        }

        if (collision.gameObject.tag == "Red")
        {
            BlueScore.score += 1;
            transform.position = new Vector3(0, 1, 5);
            sx = Random.Range(0, 2) == 0 ? -1 : 1;
            sy = Random.Range(0, 2) == 0 ? -1 : 1;
            GetComponent<Rigidbody>().velocity = new Vector3(speed * sx, speed * sy, 0f);
        }
    }

}
