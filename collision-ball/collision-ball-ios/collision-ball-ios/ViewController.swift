//
//  ViewController.swift
//  collision-ball-ios
//
//  Created by Jacob Su on 10/5/17.
//  Copyright © 2017 Jacob Su. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    var circles : [Circle]  = []
    let N = 10
    let R = 50
    
    var isAnimate : Bool = true
    
    var collisionView : CollisionBallView?
    
    weak var timer : Timer?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        self.view.backgroundColor = UIColor.lightGray
        
        let view = CollisionBallView()
        view.backgroundColor = UIColor.lightGray
        view.frame = self.view.bounds
        view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        
        self.view.addSubview(view)
        collisionView = view
        
        let sceneWidth = UInt32(self.view.bounds.width)
        let sceneHeight = UInt32(self.view.bounds.height)
        
        for _ in 1...N {
            let x = Int(arc4random_uniform(sceneWidth - UInt32(2 * R)) + UInt32(R))
            let y = Int(arc4random_uniform(sceneHeight - UInt32(2*R)) + UInt32(R))
            let vx = Int(arc4random_uniform(UInt32(11))) - 5
            let vy = Int(arc4random_uniform(11)) - 5

            circles.append(Circle(x: x, y: y, r: R, vx: vx, vy: vy, isFilled : false))
        }

        startAnimator()
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(self.handleTap(_:)));
        collisionView?.addGestureRecognizer(tap)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @objc func handleTap(_ sender: UITapGestureRecognizer) {
    
        let x = sender.location(in: collisionView).x
        let y = sender.location(in: collisionView).y
        
        let shouldStartAnimation = !self.isAnimate
        var shouldPause = true
        
        for i in 0..<circles.count {
            if (circles[i].contain(px: CFloat(x), py: CFloat(y))) {
                print("filled: x, y) = \(x) \(y)")
                circles[i].isFilled = !circles[i].isFilled
                shouldPause = false
            }
        }
        
        if shouldPause && !shouldStartAnimation {
            isAnimate = false
        }
        
        if shouldStartAnimation {
            startAnimator()
        }
    }

    func startAnimator() {
        isAnimate = true
        
        DispatchQueue.global().async {
            while (self.isAnimate) {
                self.run()
                usleep(40000)
            }
        }
        
    }

    func stopAnimator() {
        isAnimate = false
    }
    
    deinit {
        stopAnimator()
    }
    
    private func run() {
        guard let collisionView = collisionView else {
            return
        }
        
        DispatchQueue.main.async {
            collisionView.render(circles: self.circles)
        }
    
        for i in 0..<circles.count {
            circles[i].move(minx: 0, miny: 0, maxx: Int(collisionView.frame.width), maxy: Int(collisionView.frame.height))
        }
        
    }
}

